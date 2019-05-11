
$(function () {

    $('#listTime').datepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        clearBtn: true ,
        autoclose: true
    });

    $('#establishTime').datepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        clearBtn: true ,
        autoclose: true
    });

    var id = $("#id").val();
    if(!isEmpty(id)){
        $.APIPost("/api/company/getCompanyBaseInformationById?id="+id ,function (response) {
            console.log(response);
            $("#companyName").val(response.data.result.companyName);
            $("#companyRegion").val(response.data.result.companyRegion);
            $("#establishTime").val(response.data.result.establishTime);
            $("#registerMoney").val(response.data.result.registerMoney);
            $("#companyIndustry").val(response.data.result.companyIndustry);
            $("#companyIndustryDetail").val(response.data.result.companyIndustryDetail);
            $("#listCode").val(response.data.result.listCode);
            $("#listName").val(response.data.result.listName);
            $("#listTime").val(response.data.result.listTime);
            $("#listMarket").val(response.data.result.listMarket);
            $("#listSector").val(response.data.result.listSector);
            $("#listNumber").val(response.data.result.listNumber);
            $("#listPrice").val(response.data.result.listPrice);
            $("#listMoney").val(response.data.result.listMoney);
            $("#listIndustry").val(response.data.result.listIndustry);
            $("#listOrder").val(response.data.result.listOrder);
            $("#mainRelateIndustry").val(response.data.result.mainRelateIndustry);
            $("#viceRelateIndustryOne").val(response.data.result.viceRelateIndustryOne);
            $("#viceRelateIndustryTwo").val(response.data.result.viceRelateIndustryTwo);
            $("#viceRelateIndustryThree").val(response.data.result.viceRelateIndustryThree);
            $("#viceRelateIndustryFour").val(response.data.result.viceRelateIndustryFour);
            $("#viceRelateIndustryFive").val(response.data.result.viceRelateIndustryFive);
            $("#mainProduceProduct").val(response.data.result.mainProduceProduct);
            $("#viceProduceProductOne").val(response.data.result.viceProduceProductOne);
            $("#viceProduceProductTwo").val(response.data.result.viceProduceProductTwo);
            $("#viceProduceProductThree").val(response.data.result.viceProduceProductThree);
            $("#viceProduceProductFour").val(response.data.result.viceProduceProductFour);
            $("#viceProduceProductFive").val(response.data.result.viceProduceProductFive);
            $("#viceProduceProductSix").val(response.data.result.viceProduceProductSix);
            $("#viceProduceProductSeven").val(response.data.result.viceProduceProductSeven);
            $.each(response.data.result.businessArea.split(","), function(i, n){
                $("input[name='businessArea'][@value='"+n+"']").attr("checked",true);
            });
        })
    }

    $("#submitFormButton").click(function () {
        var id = $("#id").val();
        var companyName = $("#companyName").val();
        if(isEmpty(companyName)){
            showFailedAlert("公司名称不能为空！");
            return ;
        }
        var companyRegion = $("#companyRegion").val();
        if(isEmpty(companyRegion)){
            showFailedAlert("所属区域不能为空！");
            return ;
        }
        var establishTime = $("#establishTime").val();
        if(isEmpty(establishTime)){
            showFailedAlert("成立时间不能为空！");
            return ;
        }
        var registerMoney = $("#registerMoney").val();
        if(isEmpty(registerMoney)){
            showFailedAlert("注册资本不能为空！");
            return ;
        }
        var companyIndustry = $("#companyIndustry").val();
        if(isEmpty(companyIndustry)){
            showFailedAlert("所属行业不能为空！");
            return ;
        }
        var companyIndustryDetail = $("#companyIndustryDetail").val();
        if(isEmpty(companyIndustryDetail)){
            showFailedAlert("行业细分不能为空！");
            return ;
        }
        var listCode = $("#listCode").val();
        if(isEmpty(listCode)){
            showFailedAlert("市场编码不能为空！");
            return ;
        }
        var listName = $("#listName").val();
        if(isEmpty(listName)){
            showFailedAlert("市场名称不能为空！");
            return ;
        }
        var listTime = $("#listTime").val();
        if(isEmpty(listTime)){
            showFailedAlert("上市时间不能为空！");
            return ;
        }
        var listMarket = $("#listMarket").val();
        var listSector = $("#listSector").val();
        var listNumber = $("#listNumber").val();
        if(isEmpty(listNumber)){
            showFailedAlert("发行总量不能为空！");
            return ;
        }
        var listPrice = $("#listPrice").val();
        if(isEmpty(listPrice)){
            showFailedAlert("发行价格不能为空！");
            return ;
        }
        var listMoney = $("#listMoney").val();
        if(isEmpty(listMoney)){
            showFailedAlert("发行市值不能为空！");
            return ;
        }
        var listIndustry = $("#listIndustry").val();
        if(isEmpty(listIndustry)){
            showFailedAlert("市场行业不能为空！");
            return ;
        }
        var listOrder = $("#listOrder").val();
        if(isEmpty(listOrder)){
            showFailedAlert("市值排名不能为空！");
            return ;
        }
        var mainRelateIndustry = $("#mainRelateIndustry").val();
        if(isEmpty(mainRelateIndustry)){
            showFailedAlert("主营行业不能为空！");
            return ;
        }
        var viceRelateIndustryOne = $("#viceRelateIndustryOne").val();
        var viceRelateIndustryTwo = $("#viceRelateIndustryTwo").val();
        var viceRelateIndustryThree = $("#viceRelateIndustryThree").val();
        var viceRelateIndustryFour = $("#viceRelateIndustryFour").val();
        var viceRelateIndustryFive = $("#viceRelateIndustryFive").val();
        var mainProduceProduct = $("#mainProduceProduct").val();
        if(isEmpty(mainProduceProduct)){
            showFailedAlert("主营产品不能为空！");
            return ;
        }
        var viceProduceProductOne = $("#viceProduceProductOne").val();
        var viceProduceProductTwo = $("#viceProduceProductTwo").val();
        var viceProduceProductThree = $("#viceProduceProductThree").val();
        var viceProduceProductFour = $("#viceProduceProductFour").val();
        var viceProduceProductFive = $("#viceProduceProductFive").val();
        var viceProduceProductSix = $("#viceProduceProductSix").val();
        var viceProduceProductSeven = $("#viceProduceProductSeven").val();
        var businessArea = '';
        $.each($('input:checkbox:checked'),function(){
            businessArea +=  $(this).val() + ',';
        });
        if(isEmpty(businessArea)){
            showFailedAlert("运营地区不能为空！");
            return ;
        }
        $.APIPost("/api/company/editCompanyBaseInformation",JSON.stringify({
            id:id ,
            companyName:companyName ,
            companyRegion:companyRegion ,
            establishTime:establishTime ,
            registerMoney:registerMoney ,
            companyIndustry:companyIndustry ,
            companyIndustryDetail:companyIndustryDetail ,
            listCode:listCode ,
            listName:listName ,
            listTime:listTime ,
            listMarket:listMarket ,
            listSector:listSector ,
            listNumber:listNumber ,
            listPrice:listPrice ,
            listMoney:listMoney ,
            listIndustry:listIndustry ,
            listOrder:listOrder ,
            businessArea:businessArea ,
            mainRelateIndustry:mainRelateIndustry ,
            viceRelateIndustryOne:viceRelateIndustryOne ,
            viceRelateIndustryTwo:viceRelateIndustryTwo ,
            viceRelateIndustryThree:viceRelateIndustryThree ,
            viceRelateIndustryFour:viceRelateIndustryFour ,
            viceRelateIndustryFive:viceRelateIndustryFive ,
            mainProduceProduct:mainProduceProduct ,
            viceProduceProductOne:viceProduceProductOne ,
            viceProduceProductTwo:viceProduceProductTwo ,
            viceProduceProductThree:viceProduceProductThree ,
            viceProduceProductFour:viceProduceProductFour ,
            viceProduceProductFive:viceProduceProductFive ,
            viceProduceProductSix:viceProduceProductSix ,
            viceProduceProductSeven:viceProduceProductSeven
        }),function (data) {
            if(data.success){
                showSuccessAlert(data.message,function () {
                    location.href="/company/companyInformationList";
                });
            }else{
                showFailedAlert(data.message);
            }
        })
    });

    $("#resetFormButton").click(function () {
        backLastBread();
        location.href="/company/companyInformationList";
    });

});
