package com.sky.api;

import com.sky.core.controller.BaseController;
import com.sky.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


/**
 * Created by ThinkPad on 2018/10/13.
 */
public class AbstractController extends BaseController {

    @Value("${system.files.diary-text-path}")
    protected  String diaryTextPath;

    @Autowired
    protected SystemMenuService systemMenuService ;

    @Autowired
    protected SystemRoleMenuService systemRoleMenuService ;

    @Autowired
    protected SystemRoleService systemRoleService ;

    @Autowired
    protected SystemUserService systemUserService ;

    @Autowired
    protected SystemUserRoleService systemUserRoleService ;

    @Autowired
    protected SystemParamService systemParamService ;

    @Autowired
    protected LearnEnglishWordService learnEnglishWordService ;

    @Autowired
    protected LearnDiaryService learnDiaryService;

    @Autowired
    protected LearnTaskService learnTaskService ;

    @Autowired
    protected LearnQuestionService learnQuestionService ;

    @Autowired
    protected FinanceMarketService financeMarketService;

    @Autowired
    protected TraditionMarketService traditionMarketService ;

    @Autowired
    protected LearnEnglishClassService learnEnglishClassService ;

    @Autowired
    protected StockIndexService stockIndexService ;

    @Autowired
    protected EconomyNewsInfluenceService economyNewsInfluenceService ;

    @Autowired
    protected EconomyNewsStatictisService economyNewsStatictisService ;

    @Autowired
    protected InvestForexReplayService investForexReplayService ;

    @Autowired
    protected InvestStockReplayService investStockReplayService ;

    @Autowired
    protected InvestForexReplayDetailService investForexReplayDetailService ;

    @Autowired
    protected InvestStockReplayDetailService investStockReplayDetailService ;

    @Autowired
    protected InvestForexOperateService investForexOperateService ;

    @Autowired
    protected StockCompanyProductService stockCompanyProductService;

    @Autowired
    protected StockCompanyBaseService stockCompanyBaseService ;

    @Autowired
    protected StockCompanyAnalyseService stockCompanyAnalyseService ;

    @Autowired
    protected StockMarketClassService stockMarketClassService ;

}
