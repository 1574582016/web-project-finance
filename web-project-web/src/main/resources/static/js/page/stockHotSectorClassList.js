$(function () {
    var arrs = [];
    $.each(classList,function(key,value){
        var firstSector = value.firstSector;
        var just = false ;
        $.each(arrs,function(key2,value2){
            if(firstSector == value2){
                just = true ;
            }
        });

        if(!just){
            arrs.push(firstSector);
        }

    });

    var firstSectorOption = '<option value="">请选择</option>';
    $.each(arrs,function(key,value){
        firstSectorOption += '<option value="'+ value +'">'+ value +'</option>';
    });

    $("#first_sector").html(firstSectorOption);


    $("#first_sector").change(function () {
        var firstSector = $(this).val();
        var arrs2 = [];
        $.each(classList,function(key,value){
            if(value.firstSector == firstSector){
                var secondSector = value.secondSector;
                var just = false ;
                $.each(arrs2,function(key2,value2){
                    if(secondSector == value2){
                        just = true ;
                    }
                });

                if(!just){
                    arrs2.push(secondSector);
                }
            }
        });
        var secondSectorOption = '<option value="">请选择</option>';
        $.each(arrs2,function(key,value){
            secondSectorOption += '<option value="'+ value +'">'+ value +'</option>';
        });

        $("#second_sector").html(secondSectorOption);
        $("#third_sector").html( '<option value="">请选择</option>');
        $("#forth_sector").html( '<option value="">请选择</option>');
        $("#five_sector").html( '<option value="">请选择</option>');
    });

    $("#second_sector").change(function () {
        var firstSector = $("#first_sector").val();
        var secondSector = $(this).val();
        var arrs2 = [];
        $.each(classList,function(key,value){
            if(value.firstSector == firstSector && value.secondSector == secondSector){
                var thirdSector = value.thirdSector;
                var just = false ;
                $.each(arrs2,function(key2,value2){
                    if(thirdSector == value2){
                        just = true ;
                    }
                });

                if(!just){
                    arrs2.push(thirdSector);
                }
            }
        });
        var thirdSectorOption = '<option value="">请选择</option>';
        $.each(arrs2,function(key,value){
            thirdSectorOption += '<option value="'+ value +'">'+ value +'</option>';
        });

        $("#third_sector").html(thirdSectorOption);
        $("#forth_sector").html( '<option value="">请选择</option>');
        $("#five_sector").html( '<option value="">请选择</option>');
    });

    $("#third_sector").change(function () {
        var firstSector = $("#first_sector").val();
        var secondSector = $("#second_sector").val();
        var thirdSector = $(this).val();
        var arrs2 = [];
        $.each(classList,function(key,value){
            if(value.firstSector == firstSector && value.secondSector == secondSector && value.thirdSector == thirdSector){
                var forthSector = value.forthSector;
                var just = false ;
                $.each(arrs2,function(key2,value2){
                    if(forthSector == value2){
                        just = true ;
                    }
                });

                if(!just){
                    arrs2.push(forthSector);
                }
            }
        });
        var forthSectorOption = '<option value="">请选择</option>';
        $.each(arrs2,function(key,value){
            forthSectorOption += '<option value="'+ value +'">'+ value +'</option>';
        });

        $("#forth_sector").html(forthSectorOption);
        $("#five_sector").html( '<option value="">请选择</option>');
    });


    $("#forth_sector").change(function () {
        var firstSector = $("#first_sector").val();
        var secondSector = $("#second_sector").val();
        var thirdSector = $("#third_sector").val();
        var forthSector = $(this).val();
        var arrs2 = [];
        $.each(classList,function(key,value){
            if(value.firstSector == firstSector && value.secondSector == secondSector && value.thirdSector == thirdSector && value.forthSector == forthSector){
                var hotCode = value.hotCode;
                var just = false ;
                $.each(arrs2,function(key2,value2){
                    if(hotCode == value2.hotCode){
                        just = true ;
                    }
                });

                if(!just){
                    arrs2.push(value);
                }
            }
        });
        var fiveSectorOption = '<option value="">请选择</option>';
        $.each(arrs2,function(key,value){
            fiveSectorOption += '<option value="'+ value.hotCode +'">'+ value.hotName +'</option>';
        });

        $("#five_sector").html(fiveSectorOption);
    });
});