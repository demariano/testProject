'use strict';

/**
 * @ngdoc overview
 * @name clientApp
 * @description
 * # clientApp
 *
 * Main module of the application.
 */
angular
  .module('clientApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'ui.bootstrap',
    'mwl.confirm',
    'toaster',
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/notes', {
        templateUrl: 'views/notes.html',
        controller: 'NotesCtrl',
        controllerAs: 'notes'
      })
      .when('/newNotes', {
        templateUrl: 'views/newnotes.html',
        controller: 'NewnotesCtrl',
        controllerAs: 'newNotes'
      })
      .when('/viewNotes/:record_id', {
        templateUrl: 'views/viewnotes.html',
        controller: 'ViewnotesCtrl',
        controllerAs: 'viewNotes'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
