(defn find_Index
  "	Write a cljthon function to find the index of smallest triangular number with n digits. https://www.geeksforgeeks.org/index-of-smallest-triangular-number-with-n-digits/"
  [n]
  (let [n (long (or n 0))]
    (if (<= n 0)
      0
      (let [target (bigint (if (= n 1)
                             1
                             (reduce *' (repeat (dec n) 10))))
            triangular (fn [k] (/ (*' k (inc k)) 2))]
        (loop [low 1
               high 1]
          (if (>= (triangular high) target)
            (loop [l low
                   h high]
              (if (>= l h)
                l
                (let [mid (quot (+ l h) 2)]
                  (if (>= (triangular mid) target)
                    (recur l mid)
                    (recur (inc mid) h)))))
            (recur high (*' 2 high))))))))