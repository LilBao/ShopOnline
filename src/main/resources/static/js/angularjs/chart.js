var app = angular.module('myApp', []);
var host = "http://localhost:8080/api/";
// Chart
// Global Options
Chart.defaults.global.defaultFontFamily = 'Lato';
Chart.defaults.global.defaultFontSize = 18;
Chart.defaults.global.defaultFontColor = '#777';
app.controller('myCtrl', function ($scope, $http) {
    //chart Revenue of week
    $http.get(host + "revenue-of-week").then(resp => {
        let myCchartRevenueOfWeekhart = document.getElementById('chartRevenueOfWeek').getContext('2d');
        let massPopChart = new Chart(chartRevenueOfWeek, {
            type: 'line', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
            data: {
                labels: ['Monday','Tuesday','Webnesday','Thursday','Friday','Saturday','Sunday'],
                datasets: [{
                    label: 'Total',
                    data: resp.data.map(item => item[1]),
                    //backgroundColor:'green',
                    backgroundColor: 'rgba(255, 99, 132, 0.6)',
                    borderWidth: 1,
                    borderColor: '#777',
                    hoverBorderColor: '#000'
                }]
            },
            options: {
                title: {
                    display: true,
                    text: 'Revenue day of week',
                    fontSize: 20
                },
                layout: {
                    padding: {
                        left: 50,
                        right: 0,
                        bottom: 0,
                        top: 0
                    }
                },
                tooltips: {
                    enabled: true
                }
            }
        });
    })

    //chart customer
    $http.get(host + "getTop5").then(resp => {
        let myChart = document.getElementById('myChart').getContext('2d');
        let massPopChart = new Chart(myChart, {
            type: 'bar', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
            data: {
                labels: resp.data.map(item => item[0]),
                datasets: [{
                    label: 'Total',
                    data: resp.data.map(item => item[3]),
                    //backgroundColor:'green',
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.6)',
                        'rgba(54, 162, 235, 0.6)',
                        'rgba(255, 206, 86, 0.6)',
                        'rgba(75, 192, 192, 0.6)',
                        'rgba(153, 102, 255, 0.6)',
                        'rgba(255, 159, 64, 0.6)',
                        'rgba(255, 99, 132, 0.6)'
                    ],
                    borderWidth: 1,
                    borderColor: '#777',
                    hoverBorderColor: '#000'
                }]
            },
            options: {
                title: {
                    display: true,
                    text: 'Top 5 customer',
                    fontSize: 20
                },
                layout: {
                    padding: {
                        left: 50,
                        right: 0,
                        bottom: 0,
                        top: 0
                    }
                },
                tooltips: {
                    enabled: true
                }
            }
        });
    })

    //chart listInventory
    $http.get(host + "inventory").then(resp => {
        $scope.listInventory = resp.data;
        let chartInventory = document.getElementById('chartInventory').getContext('2d');
        let massPopChart = new Chart(chartInventory, {
            type: 'horizontalBar', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
            data: {
                labels: resp.data.map(item => item[2] + ' - ' + item[1]),
                datasets: [{
                    label: 'Total price',
                    data: resp.data.map(item => item[4]),
                    //backgroundColor:'green',
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.6)',
                        'rgba(54, 162, 235, 0.6)',
                        'rgba(255, 206, 86, 0.6)',
                        'rgba(75, 192, 192, 0.6)',
                        'rgba(153, 102, 255, 0.6)',
                        'rgba(255, 159, 64, 0.6)',
                        'rgba(255, 99, 132, 0.6)'
                    ],
                    borderWidth: 1,
                    borderColor: '#777',
                    hoverBorderColor: '#000'
                }, {
                    label: 'quantity',
                    data: resp.data.map(item => item[3]),
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.6)',
                        'rgba(54, 162, 235, 0.6)',
                        'rgba(255, 206, 86, 0.6)',
                        'rgba(75, 192, 192, 0.6)',
                        'rgba(153, 102, 255, 0.6)',
                        'rgba(255, 159, 64, 0.6)',
                        'rgba(255, 99, 132, 0.6)'
                    ],
                }]
            },
        });
    })
    // chart topPrdFavor
    $http.get(host + "top-product-favorite").then(resp => {
        $scope.topPrdFavor = resp.data;
        let chartTopPrdFavor = document.getElementById('chartTopPrdFavor').getContext('2d');
        let massPopChart = new Chart(chartTopPrdFavor, {
            type: 'bar', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
            data: {
                labels: resp.data.map(item => item[1] + ' - ' + item[0]),
                datasets: [{
                    label: 'Number of favorites',
                    data: resp.data.map(item => item[4]),
                    //backgroundColor:'green',
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.6)',
                        'rgba(54, 162, 235, 0.6)',
                        'rgba(255, 206, 86, 0.6)',
                        'rgba(75, 192, 192, 0.6)',
                        'rgba(153, 102, 255, 0.6)',
                        'rgba(255, 159, 64, 0.6)',
                        'rgba(255, 99, 132, 0.6)'
                    ],
                    borderWidth: 1,
                    borderColor: '#777',
                    hoverBorderColor: '#000'
                }]
            }
        });
    })
    // chart topPrdFeedback
    $http.get(host + "top-product-feedback").then(resp => {
        $scope.topPrdFeedback = resp.data;
        let chartTopPrdFeedBack = document.getElementById('chartTopPrdFeedBack').getContext('2d');
        let massPopChart = new Chart(chartTopPrdFeedBack, {
            type: 'bar', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
            data: {
                labels: resp.data.map(item => item[1] + ' - ' + item[0]),
                datasets: [{
                    label: 'Total',
                    data: resp.data.map(item => item[4]),
                    //backgroundColor:'green',
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.6)',
                        'rgba(54, 162, 235, 0.6)',
                        'rgba(255, 206, 86, 0.6)',
                        'rgba(75, 192, 192, 0.6)',
                        'rgba(153, 102, 255, 0.6)',
                        'rgba(255, 159, 64, 0.6)',
                        'rgba(255, 99, 132, 0.6)'
                    ],
                    borderWidth: 1,
                    borderColor: '#777',
                    hoverBorderColor: '#000'
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                },
                layout: {
                    padding: {
                        left: 50,
                        right: 0,
                        bottom: 0,
                        top: 0
                    }
                },
                tooltips: {
                    enabled: true
                }
            }
        });
    })
    // chart bestSelling
    $http.get(host + "best-selling").then(resp => {
        $scope.bestSelling = resp.data;
        let chartBestSelling = document.getElementById('chartBestSelling').getContext('2d');
        let massPopChart = new Chart(chartBestSelling, {
            type: 'bar', // bar, horizontalBar, pie, line, doughnut, radar, polarArea
            data: {
                labels: resp.data.map(item => item[1] + ' - ' + item[0]),
                datasets: [{
                    label: 'Total',
                    data: resp.data.map(item => item[4]),
                    //backgroundColor:'green',
                    backgroundColor: [
                        'rgba(255, 99, 132, 0.6)',
                        'rgba(54, 162, 235, 0.6)',
                        'rgba(255, 206, 86, 0.6)',
                        'rgba(75, 192, 192, 0.6)',
                        'rgba(153, 102, 255, 0.6)',
                        'rgba(255, 159, 64, 0.6)',
                        'rgba(255, 99, 132, 0.6)'
                    ],
                    borderWidth: 1,
                    borderColor: '#777',
                    hoverBorderColor: '#000',
                }]
            },
            options: {
                animation: {
                    tension: {
                        duration: 1000,
                        easing: 'linear',
                        from: 1,
                        to: 0,
                        loop: true,
                    }
                },
                scales: {
                    y: {
                        beginAtZero: true
                    },
                    x: {
                        title: false
                    }
                },
                plugins: {
                    legend: {
                        display: true,
                        position: 'top'
                    },
                    tooltips: {
                        enabled: true
                    }
                }
            }
        });
    })
})

