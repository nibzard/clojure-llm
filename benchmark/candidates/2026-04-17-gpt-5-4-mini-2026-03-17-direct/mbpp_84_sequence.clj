(defn sequence
  "Write a function to find the nth number in the Newman Conway sequence."
  [n]
  (letfn [(term [k]
            (cond
              (<= k 0) nil
              (<= k 2) 1
              :else (let [prev (vec (map term (range 1 k)))]
                      (+ (nth prev (dec (nth prev (dec k))))
                         (nth prev (- k (nth prev (dec (nth prev (dec k))))))))))]
    (term n)))