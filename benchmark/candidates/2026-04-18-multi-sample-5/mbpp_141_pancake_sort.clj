(defn pancake_sort
  "	Write a function to sort a list of elements."
  [nums]
  (letfn [(flip-prefix [v k]
            (into (subvec v k) (rseq (subvec v 0 k))))
          (max-index [v end]
            (reduce (fn [max-i i]
                      (if (pos? (compare (v i) (v max-i))) i max-i))
                    0
                    (range 1 end)))
          (sort-step [v size]
            (if (<= size 1)
              v
              (let [mi (max-index v size)]
                (cond
                  (= mi (dec size))
                  (recur v (dec size))

                  (zero? mi)
                  (recur (flip-prefix v size) (dec size))

                  :else
                  (recur (flip-prefix (flip-prefix v (inc mi)) size) (dec size))))))]
    (if (nil? nums)
      nil
      (let [v (vec nums)]
        (seq (sort-step v (count v)))))))