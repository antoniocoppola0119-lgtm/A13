package robot

import (
	"github.com/alarmfox/game-repository/model"
	"time"
)

type Robot struct {
	ID                        int64     `json:"id"`
	TestClassId               string    `json:"testClassId"`
	CreatedAt                 time.Time `json:"createdAt"`
	UpdatedAt                 time.Time `json:"updatedAt"`
	Difficulty                string    `json:"difficulty"`
	Type                      string    `json:"type"`
	Coverage                  string    `json:"coverage"`
	JacocoLineCovered         int       `json:"jacocoLineCovered"`
	JacocoBranchCovered       int       `json:"jacocoBranchCovered"`
	JacocoInstructionCovered  int       `json:"jacocoInstructionCovered"`
	JacocoLineMissed          int       `json:"jacocoLineMissed"`
	JacocoBranchMissed        int       `json:"jacocoBranchMissed"`
	JacocoInstructionMissed   int       `json:"jacocoInstructionMissed"`
	EvoSuiteLine              int       `json:"evoSuiteLine"`
	EvoSuiteBranch            int       `json:"evoSuiteBranch"`
	EvoSuiteException         int       `json:"evoSuiteException"`
	EvoSuiteWeakMutation      int       `json:"evoSuiteWeakMutation"`
	EvoSuiteOutput            int       `json:"evoSuiteOutput"`
	EvoSuiteMethod            int       `json:"evoSuiteMethod"`
	EvoSuiteMethodNoException int       `json:"evoSuiteMethodNoException"`
	EvoSuiteCBranch           int       `json:"evoSuiteCBranch"`
}

type CreateSingleRequest struct {
	TestClassId               string `json:"testClassId"`
	Difficulty                string `json:"difficulty"`
	Type                      string `json:"type"`
	Coverage                  string `json:"coverage"`
	JacocoLineCovered         int    `json:"jacocoLineCovered"`
	JacocoBranchCovered       int    `json:"jacocoBranchCovered"`
	JacocoInstructionCovered  int    `json:"jacocoInstructionCovered"`
	JacocoLineMissed          int    `json:"jacocoLineMissed"`
	JacocoBranchMissed        int    `json:"jacocoBranchMissed"`
	JacocoInstructionMissed   int    `json:"jacocoInstructionMissed"`
	EvoSuiteLine              int    `json:"evoSuiteLine"`
	EvoSuiteBranch            int    `json:"evoSuiteBranch"`
	EvoSuiteException         int    `json:"evoSuiteException"`
	EvoSuiteWeakMutation      int    `json:"evoSuiteWeakMutation"`
	EvoSuiteOutput            int    `json:"evoSuiteOutput"`
	EvoSuiteMethod            int    `json:"evoSuiteMethod"`
	EvoSuiteMethodNoException int    `json:"evoSuiteMethodNoException"`
	EvoSuiteCBranch           int    `json:"evoSuiteCBranch"`
}

type AvailableRobot struct {
	TestClassId string `json:"testClassId"`
	RobotType   string `json:"robotType"`
	Difficulty  int    `json:"difficulty"`
}

type EvoSuiteCoverage struct {
	EvoSuiteLine              int `json:"evoSuiteLine"`
	EvoSuiteBranch            int `json:"evoSuiteBranch"`
	EvoSuiteException         int `json:"evoSuiteException"`
	EvoSuiteWeakMutation      int `json:"evoSuiteWeakMutation"`
	EvoSuiteOutput            int `json:"evoSuiteOutput"`
	EvoSuiteMethod            int `json:"evoSuiteMethod"`
	EvoSuiteMethodNoException int `json:"evoSuiteMethodNoException"`
	EvoSuiteCBranch           int `json:"evoSuiteCBranch"`
}

func (r CreateSingleRequest) Validate() error {
	return nil
}

type CreateRequest struct {
	Robots []CreateSingleRequest `json:"robots"`
}

func (robots CreateRequest) Validate() error {
	return nil
}

type UpdateRequest struct {
	Scores     string `json:"scores"`
	Difficulty string `json:"difficulty"`
}

func (UpdateRequest) Validate() error {
	return nil
}

type CustomString string

// CustomString is a dummy type that implements Convertable and Validable interfaces
func (CustomString) Parse(s string) (CustomString, error) {
	return CustomString(s), nil
}

func (s CustomString) AsString() string {
	return string(s)
}

func (CustomString) Validate() error {
	return nil
}

func fromModel(r *model.Robot) *Robot {
	return &Robot{
		ID:                        r.ID,
		CreatedAt:                 r.CreatedAt,
		UpdatedAt:                 r.UpdatedAt,
		TestClassId:               r.TestClassId,
		Difficulty:                r.Difficulty,
		Type:                      r.Type,
		Coverage:                  r.Coverage,
		JacocoLineCovered:         r.JacocoLineCovered,
		JacocoBranchCovered:       r.JacocoBranchCovered,
		JacocoInstructionCovered:  r.JacocoInstructionCovered,
		JacocoLineMissed:          r.JacocoLineMissed,
		JacocoBranchMissed:        r.JacocoBranchMissed,
		JacocoInstructionMissed:   r.JacocoInstructionMissed,
		EvoSuiteLine:              r.EvoSuiteLine,
		EvoSuiteBranch:            r.EvoSuiteBranch,
		EvoSuiteException:         r.EvoSuiteException,
		EvoSuiteWeakMutation:      r.EvoSuiteWeakMutation,
		EvoSuiteOutput:            r.EvoSuiteOutput,
		EvoSuiteMethod:            r.EvoSuiteMethod,
		EvoSuiteMethodNoException: r.EvoSuiteMethodNoException,
		EvoSuiteCBranch:           r.EvoSuiteCBranch,
	}
}
