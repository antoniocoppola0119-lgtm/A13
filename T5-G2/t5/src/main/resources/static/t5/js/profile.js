/*
 * profile.js
 * Gestione livello, esperienza, progress bar e rank utente
 */

// ---------------------------
//    CONFIGURAZIONE RANGHI
// ---------------------------

const availableAvatars = [
    "default.png",
    "men-1.png",
    "men-2.png",
    "men-3.png",
    "men-4.png",
    "women-1.png",
    "women-2.png",
    "women-3.png",
    "women-4.png"
    // aggiungi tutte quelle presenti in /t5/images/profileImages/
];


const rankData = [
    {
        name: "Recluta",
        threshold: 0,
        description: "Il punto di partenza di ogni Spartan. Hai iniziato il cammino, pronto a dimostrare il tuo valore."
    },
    {
        name: "Soldato",
        threshold: 7500,
        description: "Hai superato l’addestramento di base e ora sei considerato un operatore affidabile sul campo."
    },
    {
        name: "Caporale",
        threshold: 10000,
        description: "Un soldato con esperienza, capace di coordinare piccole squadre e gestire situazioni critiche."
    },
    {
        name: "Sergente",
        threshold: 15000,
        description: "Figura di riferimento per la squadra. Il Sergente ispira disciplina, ordine e rapidità d’azione."
    },
    {
        name: "Capitano",
        threshold: 25000,
        description: "Un leader affermato. Gestisci operazioni complesse e prendi decisioni decisive sul campo."
    },
    {
        name: "Capitano Grado 2",
        threshold: 35000,
        description: "Hai dimostrato capacità strategiche avanzate. Le tue operazioni influenzano l’intero battaglione."
    },
    {
        name: "Capitano Grado 3",
        threshold: 50000,
        description: "La massima autorità tra i Capitani. Le tue scelte determinano l’esito delle missioni più rischiose."
    },
    {
        name: "Comandante",
        threshold: 75000,
        description: "Guida naturale e tattico impeccabile. Sovrintendi operazioni militari su vasta scala."
    },
    {
        name: "Generale",
        threshold: 100000,
        description: "Una leggenda tra i ranghi militari. Le tue strategie vengono studiate, replicate e temute."
    },
    {
        name: "Eroe",
        threshold: 500000,
        description: "Il tuo nome riecheggia nei corridoi della UNSC: simbolo di speranza e forza inarrestabile."
    },
    {
        name: "Leggenda",
        threshold: 1000000,
        description: "Pochi hanno raggiunto questo livello. Le tue imprese trascendono la storia e diventano mito."
    },
    {
        name: "Erede",
        threshold: 2000000,
        description: "L’apice assoluto. Non sei solo un guerriero: sei il successore della volontà Spartan, colui che plasma il futuro."
    }
];

function openAvatarModal() {
    console.log("openAvatarModal called");
    const modal = document.getElementById("avatarPickerModal");
    const list = document.getElementById("avatarList");

    list.innerHTML = ""; // reset

    availableAvatars.forEach(img => {
        const element = document.createElement("img");
        element.src = `/t5/images/profileImages/${img}`;
        element.classList.add("avatar-choice");

        element.addEventListener("click", () => selectAvatar(img));

        list.appendChild(element);
    });

    modal.classList.add("active");

}

function selectAvatar(filename) {
    console.log("selectAvatar called");
    const avatarImg = document.getElementById("profileAvatar");

    if (avatarImg) {
        avatarImg.src = `/t5/images/profileImages/${filename}`;
    }

    const hiddenInput = document.getElementById("selectedAvatarInput");
    if (hiddenInput) {
        hiddenInput.value = filename;
    }

}

function closeAvatarModal() {
    console.log("closeAvatarModal called");
    const modal = document.getElementById("avatarPickerModal");
    modal.classList.remove("active");
}


// ---------------------------
//    FUNZIONI UTILI
// ---------------------------

function getLocalImage(rankName) {
    return `/t5/images/ranks/${rankName.toLowerCase().replace(/ /g, "_")}.png`;
}

function formatNumber(num) {
    return num.toLocaleString();
}

// ---------------------------
//    CALCOLO DEL RANGO
// ---------------------------

function getRank(userExp) {
    let current = rankData[0];
    let next = rankData[1];

    for (let i = 0; i < rankData.length; i++) {
        if (userExp >= rankData[i].threshold) {
            current = rankData[i];
            next = (i + 1 < rankData.length) ? rankData[i + 1] : null;
        } else {
            break;
        }
    }

    return { current, next };
}

// ---------------------------
//    AGGIORNA DATI UI
// ---------------------------

function updateRankUI() {
    const userExp = parseInt(document.getElementById("user-exp").textContent, 10) || 0;

    const { current, next } = getRank(userExp);

    const expPerLevel = next ? (next.threshold - current.threshold) : 0;

    document.getElementById("currentRankName").textContent = `${current.name}`;
    document.getElementById("nextRankCredits").textContent = expPerLevel;
    document.getElementById("currentCredits").textContent = userExp;

    document.getElementById("rankImage").src = getLocalImage(current.name);

    let progress = 100;
    if (next) {
        progress = ((userExp - current.threshold) / (next.threshold - current.threshold)) * 100;
    }
    document.getElementById("rankProgressBar").style.width = progress + "%";
    document.getElementById("rankMessage").textContent = current.description || "";

    console.log("UserExp:", userExp, "CurrentRank:", current.name, "NextRank:", next?.name, "Progress:", progress);
}

// ---------------------------
//    MODAL RANGHI
// ---------------------------


function openRankModal() {
    console.log("openRankModal called");
    const rankModal = document.getElementById("rankModal");
    rankModal.classList.add("active");
    generateRankList();

}

function closeRankModal() {
    const rankModal = document.getElementById("rankModal");
    rankModal.classList.remove("active");
}

function generateRankList() {
    const userExp = parseInt(document.getElementById("user-exp").textContent, 10) || 0;
    const list = document.getElementById("fullRankList");
    list.innerHTML = "";

    let currentIdx = 0;
    for (let i = 0; i < rankData.length; i++) {
        if (userExp >= rankData[i].threshold) currentIdx = i;
        else break;
    }

    const next = rankData[currentIdx + 1];
    if (next) {
        const diff = next.threshold - userExp;
        const pct = ((userExp - rankData[currentIdx].threshold) /
            (next.threshold - rankData[currentIdx].threshold)) * 100;

        document.getElementById("modalNextGoalText").innerHTML =
            `Mancano <strong style="color:var(--accent-cyan)">${diff.toLocaleString()} cR</strong> per <strong>${next.name}</strong>`;

        document.getElementById("modalProgressBar").style.width = pct + "%";
    }

    rankData.forEach((r, i) => {
        const li = document.createElement("li");
        li.className = `rank-list-item clickable-hover-sound tier-${r.tier}`;

        let icon = "";
        if (i < currentIdx) {
            li.classList.add("past");
            icon = `<i class="bi bi-check"></i>`;
        } else if (i === currentIdx) {
            li.classList.add("current");
            icon = `<i class="bi bi-map-marker-alt"></i>`;
            setTimeout(() => li.scrollIntoView({ block: "center" }), 100);
        } else {
            li.classList.add("locked");
            icon = `<i class="bi bi-lock"></i>`;
        }

        li.innerHTML = `
            <div class="rank-list-left">
                <img src="${getLocalImage(r.name)}" alt="" class="list-rank-img" onerror="this.style.display='none'">
                <div class="rank-list-info">
                    <span class="rank-list-combined">${r.name} / <span class="credits-text">${r.threshold.toLocaleString()} cR</span></span>
                </div>
            </div>
            <div class="rank-list-status">${icon}</div>
        `;

        list.appendChild(li);
    });
}

// ---------------------------
//    AVVIO
// ---------------------------

document.addEventListener("DOMContentLoaded", () => {

    const rankImage = document.getElementById("rankIconContainer");

    const editAvatarBtn = document.getElementById("editAvatarBtn");

    const rankModal = document.getElementById("rankModal");

    updateRankUI();

    if (rankImage) {
        rankImage.addEventListener("click", openRankModal);
    }

    if (rankModal) {
        rankModal.addEventListener("click", closeRankModal);
    }

    if (editAvatarBtn) {
        editAvatarBtn.addEventListener("click", openAvatarModal);
    }

    const avatarPickerModal = document.getElementById("avatarPickerModal");
    const avatarModalContent = avatarPickerModal.querySelector(".avatar-modal-content");

    if (avatarPickerModal) {
        // click sull'overlay → chiude
        avatarPickerModal.addEventListener("click", closeAvatarModal);
    }

// click sul contenuto → NON chiude
    if (avatarModalContent) {
        avatarModalContent.addEventListener("click", (e) => {
            e.stopPropagation();
        });
    }

});

