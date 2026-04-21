(defn sequence
  "Write a function to find the nth number in the newman conway sequence."
  [n]
  (cond
    (< n 1) nil
    (<= n 2) 1
    :else
    (loop [i 3
           p [0 1 1]]
      (if (> i n)
        (peek p)
        (let [prev (p (dec i))
              next-val (+ (p prev) (p (- i prev)))]
          (recur (inc i) (conj p next-val)))))))