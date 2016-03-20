
var anModule = angular.module('staffManagerApp', [
        'ngAnimate',
        'ui.bootstrap']);

    anModule.controller('StaffCtrl', ['$scope', '$http', function ($scope, $http) {

        $scope.contacts = [];
        $scope.selectedcontact = {};
        $scope.newcontact = {};
        $scope.showEditContact = false;
        $scope.showNewContact = false;
        $scope.showPhoto = false;


        $scope.initialize = function () {
            $scope.loadData();
        }

        $scope.loadData = function () {
            {
                $http({
                    url: "/rest/contacts/getContacts",
                    method: "GET"
                }).success(
                    function (response) {
                        $scope.contacts = response;
                        $scope.selectedcontact = [];
                        $scope.newcontact = [];
                    })
            }
        }

        //открывает окно с функцией редактирования
        $scope.edit = function (uuid) {
            for (var i = 0; i < $scope.contacts.length; i++) {
                var contact = $scope.contacts[i];
                if (contact.uuid===uuid) {
                    $scope.selectedcontact = {};
                    $scope.selectedcontact.uuid = contact.uuid;
                    $scope.selectedcontact.name = contact.name;
                    $scope.selectedcontact.sex = contact.sex;
                    $scope.selectedcontact.position = contact.position;
                    $scope.selectedcontact.age = contact.age;
                    $scope.selectedcontact.photo = contact.photo;
                    $scope.selectedcontact.desc = contact.desc;
                    break;
                }
            }
            $scope.showEditContact = true;
        }

        //показывает большое фото
        $scope.openPhoto = function (uuid) {
            for (var i = 0; i < $scope.contacts.length; i++) {
                var contact = $scope.contacts[i];
                if (contact.uuid===uuid) {
                    $scope.selectedcontact = {};
                    $scope.selectedcontact.photo = contact.photo;
                    break;
                }
            }
            $scope.showPhoto = true;
        }

        //открывает окно с вводом нового контакта
        $scope.newContact = function () {
            $scope.showNewContact = true;
        }

        //сохраняет отредактированный контакт
        $scope.save = function () {
            $http({
                url: "/rest/contacts/",
                method: "PUT",
                params: $scope.selectedcontact
            }).success(
                function (response) {
                    $scope.reloadContacts(response);
                });
            $scope.showEditContact = false;

        }

        $scope.delete = function (uuid) {
            $http({
                url: "/rest/contacts/"+uuid,
                method: "DELETE"
            }).success(
                function (response) {
                    $scope.reloadContacts(response);
                });
        }

        //открывает окно с вводом нового контакта
        $scope.newContact = function () {
            $scope.showNewContact = true;
        }


        //добавляет новый контакт
        $scope.add = function () {
            var newContact = {};
            newContact.name = $scope.newcontact.name;
            //newContact.uuid = $scope.newcontact.uuid;
            newContact.sex = $scope.newcontact.sex;
            newContact.position = $scope.newcontact.position;
            newContact.age = $scope.newcontact.age;
            newContact.photo = $scope.newcontact.photo;
            newContact.desc = $scope.newcontact.desc;

            $http({
                url: "/rest/contacts/",
                method: "POST",
                params: newContact
            }).success(
                function (response) {
                    $scope.reloadContacts(response);
                });
            $scope.showNewContact = false;

        }

        $scope.cancel = function () {
            $scope.showEditContact = false;
            $scope.showNewContact = false;
            $scope.showPhoto = false;
        }


        $scope.reloadContacts = function (contacts) {
            $scope.contacts = contacts;
            $scope.selectedcontact = {};
            $scope.newcontact = {};
        }


        $scope.initialize();

    }]);
