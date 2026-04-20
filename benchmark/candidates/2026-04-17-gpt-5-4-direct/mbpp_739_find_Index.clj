(defn find_Index
  "Write a cljthon function to find the index of smallest triangular number with n digits. https://www.geeksforgeeks.org/index-of-smallest-triangular-number-with-n-digits/"
  [n]
  (let [n (long (or n 0))]
    (if (<= n 0)
      nil
      (let [target (bigint (if (= n 1) 1 (reduce *' (repeat (dec n) 10))))]
        (loop [low 1N
               high 1N]
          (let [tri-high (/ (*' high (inc high)) 2)]
            (if (>= tri-high target)
              (loop [l low
                     h high]
                (if (< l h)
                  (let [mid (quot (+ l h) 2)
                        tri-mid (/ (*' mid (inc mid)) 2)]
                    (if (>= tri-mid target)
                      (recur l mid)
                      (recur (inc mid) h)))
                  l))
              (recur high (*' 2 high))))))))