(defn circular_shift
  "Circular shift the digits of the integer x, shift the digits right by shift
  and return the result as a string.
  If shift > number of digits, return digits reversed."
  [x shift]
  (let [s (str x)
        len (count s)]
    (if (>= shift len)
      (apply str (reverse s))
      (str (subs s (- len shift)) (subs s 0 (- len shift))))))