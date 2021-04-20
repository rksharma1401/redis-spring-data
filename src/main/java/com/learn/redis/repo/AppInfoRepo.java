package com.learn.redis.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.learn.redis.entity.AppInfo;

@Repository
public interface AppInfoRepo extends CrudRepository<AppInfo, Integer> {
}
