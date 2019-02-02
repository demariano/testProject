'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:ViewnotesCtrl
 * @description
 * # ViewnotesCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('ViewnotesCtrl', function ( $scope,notesService,$location,$routeParams,toaster) {
  
    $scope.record = {}
    $scope.editEnabled = true;
    $scope.hideSaveButton =true;
    $scope.hideEditButton =false;
    
    loadData();

    function loadData(){
      notesService.getRecord($routeParams.record_id)
      .then (function(response){
        $scope.record = response.data;
       
      },
      function(response ){
        var json = JSON.stringify(response);
        console.log("Unable to Retrieve Data from Backend Server");
      })

      
      $scope.editRecord = function(){
        $scope.hideSaveButton =false;
        $scope.hideEditButton =true;
        $scope.editEnabled = false;
      }

      $scope.backToHome = function(){
        $location.path("/notes")
      }

      $scope.saveRecord = function(){
        console.log("Updating Note Record "+ $scope.record.title +" " +  $scope.record.content);
        notesService.updateRecord($scope.record.id,$scope.record)
        .then (function(response){
          toaster.pop('success', "", "Record Save");
          $scope.hideSaveButton =true;
          $scope.hideEditButton =false;
          $scope.editEnabled = true;
       
        },
        function(response ){
          var json = JSON.stringify(response);
          console.log(response);
          toaster.pop('error', "", "Unable to Save Record");
        })
      }

  }

  });
