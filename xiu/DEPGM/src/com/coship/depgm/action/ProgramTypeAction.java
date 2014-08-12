package com.coship.depgm.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.coship.depgm.model.ProgramType;
import com.coship.depgm.service.ProgramTypeService;

/**
 * @author 908618
 * 
 */
public class ProgramTypeAction extends BaseAction {
	private static final long serialVersionUID = -561641423189435696L;

	@Autowired
	private ProgramTypeService programTypeService;

	private ProgramType programType;
	private String id;
	private String name;
	private boolean visible;
	private Integer rank;
	private String tvSouTypeId;

	public void list() throws Exception{
		list = programTypeService.queryList();
		jsonList();
	}

	public void create() throws Exception {
		programTypeService.add(name, visible, rank, tvSouTypeId);
		success();
	}

	public void update() throws Exception {
		programTypeService.update(id, name, visible, rank, tvSouTypeId);
		success();
	}

	public void delete() throws Exception {
		programTypeService.delete(id);
		success();
	}

	public void getVisibleTypeList() throws Exception, Exception {
		list = programTypeService.queryVisibleList();
		jsonList();
	}

	public ProgramType getProgramType() {
		return programType;
	}

	public void setProgramType(ProgramType programType) {
		this.programType = programType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVisible() {
		return visible;
	}


	public void setVisible(boolean visible) {
		this.visible = visible;
	}


	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getTvSouTypeId() {
		return tvSouTypeId;
	}

	public void setTvSouTypeId(String tvSouTypeId) {
		this.tvSouTypeId = tvSouTypeId;
	}
}