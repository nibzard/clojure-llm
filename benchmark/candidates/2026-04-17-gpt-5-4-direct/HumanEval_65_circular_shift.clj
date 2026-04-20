(defn circular_shift
  "Circular shift the digits of the integer x, shift the digits right by shift
  and return the result as a string.
  If shift > number of digits, return digits reversed."
  [x shift]
  (let [s (str x)
        n (count s)]
    (cond
      (zero? n) s
      (> shift n) (apply str (reverse s))
      :else (let [k (mod shift n)]
              (str (subs s (- n k)) (subs s 0 (- n k)))))))