

export function Syaringa({ person }) {

    return (
        <>
            {/* <svg xmlns="http://www.w3.org/2000/svg" xmlnsXlink="http://www.w3.org/1999/xlink" width="500" height="500">
                <defs>
                    <g id="xly">
                        <path d="M80 25
                                A 30 30 0 0 0 64 74
                                L 67 75
                                A 14 14 0 0 1 91 71
                                L 95 69
                                C 80 66 64 34 80 25" stroke="black" />
                    </g>
                </defs>
                <circle cx="250" cy="250" r="56" fill="red" stroke="black" stroke-width="2.5" />
                <circle cx="250" cy="250" r="40" fill="red" stroke="black" stroke-width="1" />
                <circle cx="250" cy="250" r="14" fill="red" stroke="black" stroke-width="1" />
                <g id="dou" transform="translate(170, 170)">
                    <use xlinkHref="#xly">
                        <animateTransform
                            attributeType="XML"
                            attributeName="transform"
                            begin="0s"
                            type="rotate"
                            from="0 80 80"
                            to="360 80 80"
                            dur="1s"
                            repeatCount="indefinite"
                        /></use>
                    <use xlinkHref="#xly">
                        <animateTransform
                            attributeType="XML"
                            attributeName="transform"
                            begin="0s"
                            type="rotate"
                            from="120 80 80"
                            to="480 80 80"
                            dur="1s"
                            repeatCount="indefinite"
                        /></use>
                    <use xlinkHref="#xly" transform="translate(250,250) rotate(240)">
                        <animateTransform
                            attributeType="XML"
                            attributeName="transform"
                            begin="0s"
                            type="rotate"
                            from="240 80 80"
                            to="600 80 80"
                            dur="1s"
                            repeatCount="indefinite"
                        /></use>
                </g>
            </svg> */}
            <svg xmlns="http://www.w3.org/2000/svg" xmlnsXlink="http://www.w3.org/1999/xlink" width="500" height="500">
                <defs>
                    <g id="xlys">
                        <path d="M40 12.5
                    A 15 15 0 0 0 32 37
                    L 33.5 37.5
                    A 7 7 0 0 1 45.5 35.5
                    L 47.5 34.5
                    C 40 33 32 17 40 12.5" stroke="black" />
                    </g>
                </defs>
                <g >
                    <circle cx="250" cy="250" r="19" fill="red" stroke="black" stroke-width="1.25" />
                    <circle cx="250" cy="250" r="10" fill="red" stroke="black" stroke-width="0.5" />
                    <circle cx="250" cy="250" r="3.5" fill="red" stroke="black" stroke-width="0.5" />
                    <g id="dous" transform="translate(230, 230) scale(0.5)">
                        <use xlinkHref="#xlys">
                            <animateTransform
                                attributeType="XML"
                                attributeName="transform"
                                begin="0s"
                                type="rotate"
                                from="0 40 40"
                                to="-360 40 40"
                                dur="3s"
                                repeatCount="indefinite"
                            />
                        </use>
                        <use xlinkHref="#xlys">
                            <animateTransform
                                attributeType="XML"
                                attributeName="transform"
                                begin="0s"
                                type="rotate"
                                from="120 40 40"
                                to="-240 40 40"
                                dur="3s"
                                repeatCount="indefinite"
                            />
                        </use>
                        <use xlinkHref="#xlys" transform="translate(125, 125) rotate(240)">
                            <animateTransform
                                attributeType="XML"
                                attributeName="transform"
                                begin="0s"
                                type="rotate"
                                from="240 40 40"
                                to="-120 40 40"
                                dur="3s"
                                repeatCount="indefinite"
                            />
                        </use>
                    </g>
                </g>
            </svg>
        </>
    )
}