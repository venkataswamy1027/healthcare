var userApp = angular.module('app', []);

userApp.controller('MainController', function($scope, $http) {
	$scope.hospitals = [];
	$scope.showHospitalsList = false;
	$scope.showPatientsList = false;
	$scope.patients = [];
	
	$scope.HospitalsList = function() {
		$http.get('/hospital/all').then(function(response) {
			$scope.hospitals = response.data;
			$scope.showHospitalsList = true;
		});
	};
	
	$scope.showPatients = function(id) {
		$scope.hosipitalId = id;
		var url = "/hospital/" + $scope.hosipitalId;
		$http.get(url).then(function(response) {
			$scope.patients = response.data.patients;
			$scope.showPatientsList = true;
		});
	};

});