import React, { useState, useEffect, useRef } from 'react';
import { Svg } from './svg.tsx';

import './App.css';



function App() {
  const [currentTime, setCurrentTime] = useState(getCurrentTime());
  useEffect(() => {
    const intervalID = setInterval(() => {
      setCurrentTime(getCurrentTime());

    }, 1000);

    return () => clearInterval(intervalID);
  }, []);

  function getCurrentTime() {
    const now = new Date();
    return {
      year: now.getFullYear(),
      month: now.getMonth() + 1,
      date: now.getDate(),
      hours: now.getHours(),
      minutes: now.getMinutes(),
      seconds: now.getSeconds(),
    };
  }

  return (
    <div>
      <div>{currentTime.year}年{currentTime.month}月{currentTime.date}日 {currentTime.hours}时{currentTime.minutes}分{currentTime.seconds}秒</div>
      <Svg person={{
        width: "500",
        height: "500"
      }} />
     

    </div>
  );
}

export default App;
