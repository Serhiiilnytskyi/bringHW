package com.isi.bringhw.demo;

import com.isi.bringhw.Bean;

@Bean("customName2")
public class SecondBean implements SuperBean{
  @Override
  public String describe() {
    return "SecondBean";
  }
}
