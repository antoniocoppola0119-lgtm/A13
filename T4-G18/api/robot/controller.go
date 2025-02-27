package robot

import (
	"encoding/json"
	"fmt"
	"net/http"

	"github.com/alarmfox/game-repository/api"
)

type Service interface {
	CreateBulk(request *CreateRequest) (int, error)
	FindByFilter(testClassId string, difficulty string, robotType string) (Robot, error)
	DeleteByTestClass(testClassId string) error
	GetAllAvailableRobots() ([]AvailableRobot, error)
	GetEvoSuiteCoverageBy(testClassId string, difficulty string, robotType string) (EvoSuiteCoverage, error)
}

type Controller struct {
	service Service
}

func NewController(rs Service) *Controller {
	return &Controller{
		service: rs,
	}
}

func (rc *Controller) CreateBulk(w http.ResponseWriter, r *http.Request) error {

	request, err := api.FromJsonBody[CreateRequest](r.Body)
	if err != nil {
		return err
	}
	n, err := rc.service.CreateBulk(&request)
	if err != nil {
		return api.MakeHttpError(err)
	}

	return api.WriteJson(w, http.StatusCreated, map[string]any{"created": n})
}

func (rc *Controller) FindByFilter(w http.ResponseWriter, r *http.Request) error {

	testClassId, err := api.FromUrlQuery[CustomString](r, "testClassId", "")
	if err != nil {
		return err
	}

	difficulty, err := api.FromUrlQuery[CustomString](r, "difficulty", "")
	if err != nil {
		return err
	}

	robotType, err := api.FromUrlQuery[CustomString](r, "type", "")
	if err != nil {
		return err
	}

	robot, err := rc.service.FindByFilter(
		testClassId.AsString(),
		difficulty.AsString(),
		robotType.AsString(),
	)

	if err != nil {
		return api.MakeHttpError(err)
	}

	return api.WriteJson(w, http.StatusOK, robot)

}

func (rc *Controller) GetAllAvailableRobots(w http.ResponseWriter, r *http.Request) error {
	robots, err := rc.service.GetAllAvailableRobots()
	if err != nil {
		return api.MakeHttpError(err)
	}

	jsonData, _ := json.Marshal(robots)
	fmt.Println("JSON Response:", string(jsonData))

	return api.WriteJson(w, http.StatusOK, robots)
}

func (rc *Controller) GetEvoSuiteCoverageBy(w http.ResponseWriter, r *http.Request) error {
	testClassId, err := api.FromUrlQuery[CustomString](r, "testClassId", "")
	if err != nil {
		return err
	}

	difficulty, err := api.FromUrlQuery[CustomString](r, "difficulty", "")
	if err != nil {
		return err
	}

	robotType, err := api.FromUrlQuery[CustomString](r, "robotType", "")
	if err != nil {
		return err
	}

	evoSuiteCoverage, err := rc.service.GetEvoSuiteCoverageBy(
		testClassId.AsString(),
		difficulty.AsString(),
		robotType.AsString(),
	)

	if err != nil {
		return api.MakeHttpError(err)
	}

	return api.WriteJson(w, http.StatusOK, evoSuiteCoverage)
}

func (rc *Controller) Delete(w http.ResponseWriter, r *http.Request) error {
	testClassId, err := api.FromUrlQuery[CustomString](r, "testClassId", "")
	if err != nil {
		return err
	}
	if err := rc.service.DeleteByTestClass(testClassId.AsString()); err != nil {
		return api.MakeHttpError(err)
	}
	w.WriteHeader(http.StatusNoContent)
	return nil
}
