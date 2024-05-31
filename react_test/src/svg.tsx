import React, { useState, useEffect, useRef } from 'react';
import {Scale} from './svghms.tsx'
import {Syaringa} from './sarinnga.tsx'
export function Svg({ person }) {
    const [currentTime, setCurrentTime] = useState(getCurrentTime());
    const [color, setColor] = useState('lightcoral')

    useEffect(() => {
        const intervalID = setInterval(() => {
            setCurrentTime(getCurrentTime());
            setColor("#"+Math.floor(Math.random() * 0xffffff).toString(16));
        }, 1000);

        return () => clearInterval(intervalID);
    }, []);

    function getCurrentTime() {
        const now = new Date();
        return {
            hours: now.getHours(),
            minutes: now.getMinutes(),
            seconds: now.getSeconds(),
        };
    }

    const nowtime = new Date();
    var seconds = (nowtime.getSeconds() * 6) - 180
    var minutes = (nowtime.getMinutes() * 6) - 180
    var hours = (nowtime.getHours() * 30) - 180

   

    return (
        <div>
            
            <p>
                Pick a color:{' '}
                <select value={color} onChange={e => setColor(e.target.value)}>
                    <option value="lightcoral">lightcoral</option>
                    <option value="midnightblue">midnightblue</option>
                    <option value="blue">blue</option>
                    <option value="yellow">yellow</option>
                    <option value="red">red</option>
                </select>
            </p>
            <svg version="1.1"
                baseProfile="full"
                width={person.width} height={person.height}
                xmlns="http://www.w3.org/2000/svg">
                    
                <rect width="100%" height="100%" fill={color} className='watch'/>
                <circle cx="250" cy="250" r="220" fill="white" />
                <rect x="243" y="250" rx="3" width="15" height="130" fill={color} transform={`rotate(${minutes} 250 250)`} className='watch'/>
                <rect x="237" y="250" rx="5"  width="25" height="90" fill={color} transform={`rotate(${hours} 250 250)`} className='watch'/>
                <rect x="247" y="250" rx="2" width="6" height="170" fill={color} id="secondsId" className="seconds watch" transform={`rotate(${seconds} 250 250)`}/>

                <Syaringa person={{
        color: {color}
      }}/>
                <Scale/>
            </svg>
        </div>
    )
}