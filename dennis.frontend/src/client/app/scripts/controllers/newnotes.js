'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:NewnotesCtrl
 * @description
 * # NewnotesCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('NewnotesCtrl', function ($scope,notesService,$location,toaster) {
    $scope.record = {
      title :'',
      content:''
    }; 
      


    $scope.saveRecord = function(){
      console.log("Saving Note Record "+ $scope.record.title +" " +  $scope.record.content);
      notesService.createRecord($scope.record)
      .then (function(response){
    
       $location.path("/notes")
        
      },
      function(response ){
        var json = JSON.stringify(response);
        console.log(response);
        toaster.pop('error', "", "Unable to Create Record");
      })
    }

  });
