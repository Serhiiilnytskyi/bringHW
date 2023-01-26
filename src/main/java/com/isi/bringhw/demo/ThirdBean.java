package com.isi.bringhw.demo;

import com.isi.bringhw.Bean;

@Bean("customName3")
public class ThirdBean implements SuperBean {

  @Override
  public String describe() {
    return "ThirdBean";
  }
}
