import React, { useState, useEffect, useRef } from 'react';
export function Scale(){

    const radius = 199;
    const cx = 251;
    const cy = 252;
    const fontSize = 28;
    const hoursArray = Array.from({ length: 12 }, (_, i) => i + 1);
    const getHourPosition = (hour: number) => {
        const angle = (hour / 12) * 2 * Math.PI;
        return {
            x: cx + radius * Math.sin(angle),
            y: cy - radius * Math.cos(angle),
        };
    };

    return(
        <>
        {hoursArray.map(hour => {
            const pos = getHourPosition(hour);
            return (
                <text
                    key={hour}
                    x={pos.x}
                    y={pos.y}
                    fontSize={fontSize}
                    textAnchor="middle"
                    alignmentBaseline="middle"
                >
                    {hour}
                </text>
            );
        })}
        </>
        
    )
}