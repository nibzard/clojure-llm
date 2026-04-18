(defn lcs_of_three
  "	Write a function to find the longest common subsequence for the given three string sequence. https://www.geeksforgeeks.org/lcs-longest-common-subsequence-three-strings/"
  [X Y Z]
  (let [x (or X "")
        y (or Y "")
        z (or Z "")
        n (count x)
        m (count y)
        p (count z)]
    (loop [i 0
           j 0
           k 0
           dp (vec (repeat (inc p) (vec (repeat (inc m) (vec (repeat (inc n) 0))))))]
      (if (or (zero? i) (zero? j) (zero? k))
        (nth (nth (nth dp 0) 0) 0)
        (let [xi (nth x (dec i))
              yj (nth y (dec j))
              zk (nth z (dec k))]
          (cond
            (= xi yj zk)
            (recur (dec i) (dec j) (dec k)
                   (assoc-in dp [k j i]
                             (inc (nth (nth (nth dp (dec k)) (dec j)) (dec i)))))


            (or (= xi yj) (= xi zk) (= yj zk))
            (recur (dec i) (dec j) (dec k)
                   (assoc-in dp [k j i]
                             (max (nth (nth (nth dp (dec k)) (dec j)) (dec i))
                                  (nth (nth (nth dp k) (dec j)) (dec i))
                                  (nth (nth (nth dp (dec k)) j) (dec i)))))


            :else
            (recur (dec i) (dec j) (dec k)
                   (assoc-in dp [k j i]
                             (max (nth (nth (nth dp (dec k)) (dec j)) (dec i))
                                  (nth (nth (nth dp k) (dec j)) (dec i))
                                  (nth (nth (nth dp (dec k)) j) (dec i)))))))))))