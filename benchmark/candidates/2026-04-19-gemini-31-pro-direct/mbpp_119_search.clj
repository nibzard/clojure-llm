(defn search
  "Finds the element that appears only once in a sorted vector where every other element appears twice."
  [arr]
  (when (seq arr)
    (loop [low 0
           high (dec (count arr))]
      (if (>= low high)
        (nth arr low)
        (let [mid (quot (+ low high) 2)
              even-mid (if (odd? mid) (dec mid) mid)]
          (if (= (nth arr even-mid) (nth arr (inc even-mid)))
            (recur (+ even-mid 2) high)
            (recur low even-mid)))))))