(defn is_woodall
  "	Write a function to check if the given number is woodball or not."
  [x]
  (letfn [(pow2 [n]
            (loop [i n
                   acc 1N]
              (if (zero? i)
                acc
                (recur (dec i) (*' acc 2)))))
          (woodall? [n]
            (and (integer? n)
                 (pos? n)
                 (loop [k 1]
                   (let [v (*' k (dec (pow2 k)))]
                     (cond
                       (= v n) true
                       (> v n) false
                       :else (recur (inc k)))))))]
    (woodall? x)))