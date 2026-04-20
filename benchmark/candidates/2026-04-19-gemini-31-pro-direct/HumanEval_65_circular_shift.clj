(defn circular_shift [x shift]
  (let [s (str x)
        n (count s)]
    (if (> shift n)
      (apply str (reverse s))
      (str (subs s (- n shift)) (subs s 0 (- n shift))))))