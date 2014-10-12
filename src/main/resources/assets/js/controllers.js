var game = angular.module('roboRallyGame', ['ngRoute'])
        .config(['$routeProvider', '$locationProvider',
            function($routeProvider, $locationProvider) {
                $routeProvider
                        .when('/lobby', {
                            templateUrl: 'partials/lobby.html',
                            controller: 'LobbyCtrl'
                        })
                        .when('/game/:gameId', {
                            templateUrl: 'partials/game.html',
                            controller: 'GameCtrl'
                        })
                        .otherwise({redirectTo: '/lobby'})
//https://docs.angularjs.org/api/ngRoute/directive/ngView
            }]);

function LobbyCtrl($scope, $http)
{
    $http.get('/game').success(function(data) {
        console.log(data)
        $scope.games = data;
    });
}



function GameCtrl($scope, $http, $routeParams)
{
    var board = new PIXI.DisplayObjectContainer();
    $http.get('/game/' + $routeParams.gameId).success(function(data) {
        console.log(data);
        $scope.game = data;
        data.board.forEach(function(value)
        {
            value.forEach(function(tile) {

                var sprite = new PIXI.Sprite(PIXI.Texture.fromImage("images/"+tile.tileType+".png"));
                sprite.anchor.x = 0;
                sprite.anchor.y = 0;
                sprite.position.x = tile.x * 150;
                sprite.position.y = tile.y * 150;
                board.addChild(sprite)
            })
        })
        board.scale.x = 0.5;
        board.scale.y = 0.5;
        stage.addChild(board);
    });


    var stage = new PIXI.Stage(0xFFFFFF);
    var renderer = PIXI.autoDetectRenderer(1920, 1080);

    document.getElementById('game').appendChild(renderer.view);

    requestAnimFrame(animate);

    function animate() {
        requestAnimationFrame(animate);
        renderer.render(stage);
    }
}