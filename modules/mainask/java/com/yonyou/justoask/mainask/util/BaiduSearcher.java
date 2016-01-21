/**
 * 
 * APDPlat - Application Product Development Platform
 * Copyright (c) 2016, 姚磊, layour@163.com
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package com.yonyou.justoask.mainask.util;

/**
 *
 * @author 姚磊
 */
public interface BaiduSearcher extends Searcher{
    /**
     * 新闻搜索
     * @param keyword
     * @return 
     */
    public SearchResult searchNews(String keyword);
    /**
     * 新闻搜索（分页）
     * @param keyword
     * @param page
     * @return 
     */
    public SearchResult searchNews(String keyword, int page);
    /**
     * 贴吧搜索
     * @param keyword
     * @return 
     */
    public SearchResult searchTieba(String keyword);
    /**
     * 贴吧搜索（分页）
     * @param keyword
     * @param page
     * @return 
     */
    public SearchResult searchTieba(String keyword, int page);
    /**
     * 知道搜索
     * @param keyword
     * @return 
     */
    public SearchResult searchZhidao(String keyword);
    /**
     * 知道搜索（分页）
     * @param keyword
     * @param page
     * @return 
     */
    public SearchResult searchZhidao(String keyword, int page);
    /**
     * 文库搜索
     * @param keyword
     * @return 
     */
    public SearchResult searchWenku(String keyword);
    /**
     * 文库搜索（分页）
     * @param keyword
     * @param page
     * @return 
     */
    public SearchResult searchWenku(String keyword, int page);
}