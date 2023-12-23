import React from "react";

const Progress = (props) => {
  return (
    <div >
      <input
        type="range"
        min="1"
        max="100"
        step="1"
        value={props.value}
        id="myRange"
        onChange={props.onChange}
        onMouseUp={props.onMouseUp}
        onTouchEnd={props.onTouchEnd}
        style={{
          background: `linear-gradient(90deg, var(--progressUsed) ${Math.floor(
            props.value
          )}%, var(--progressLeft) ${Math.floor(props.value)}%)`,
        }}
      />
    </div>
  );
};

export default Progress;