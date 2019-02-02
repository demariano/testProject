'use strict';

/**
 * @ngdoc function
 * @name clientApp.controller:NotesCtrl
 * @description
 * # NotesCtrl
 * Controller of the clientApp
 */
angular.module('clientApp')
  .controller('NotesCtrl', function ($scope,notesService,$location,toaster) {
   

      loadData();
     
    

      $scope.createNote = function(){
        console.log("Create Note Invoked");
        $location.path("/newNotes")
      }

      $scope.viewNote = function(record){
        console.log("View Note Invoked");
        $location.path("/viewNotes/" + record.id);
      }

      $scope.deleteNote = function(record){
        console.log("Deleting Note Record :" + record.id )
        notesService.deleleRecord(record.id)
          .then(function (response){
             console.log("Record Deleted");
             toaster.pop('success', "", "Record Deleted");
             loadData();
          },
          function (reponse){
            var json = JSON.stringify(response);
           
            console.log("Unable to Delete Record");
            toaster.pop('error', "", "Unable to Delete Record");
          })
      }

      function loadData(){
          notesService.getRecords()
          .then (function(response){
            $scope.data = response.data;
            console.log($scope.data );
          },
          function(response ){
            var json = JSON.stringify(response);
            console.log("Unable to Retrieve Data from Backend Server");
            toaster.pop('error', "", "Unable to Retrieve Data from Backend Server");
          })
      }


   
    
    
  });
