var app = angular.module('myApp', []);

app.controller('personController', function($scope){
    $scope.firstName = "Ali";
    $scope.middleName = "Vali";
    $scope.lastName = "Sami";
});