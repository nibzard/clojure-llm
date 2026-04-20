(defn lcs_of_three
  "Write a function to find the longest common subsequence for the given three string sequence. https://www.geeksforgeeks.org/lcs-longest-common-subsequence-three-strings/"
  [X Y Z]
  (let [x (seq (or X ""))
        y (seq (or Y ""))
        z (seq (or Z ""))
        lx (count x)
        ly (count y)
        lz (count z)
        dp (vec (for [i (range (inc lx))]
                  (vec (for [j (range (inc ly))]
                         (vec (repeat (inc lz) 0))))))]
    (loop [i 1, dp dp]
      (if (> i lx)
        (get-in dp [lx ly lz])
        (let [xi (nth x (dec i))]
          (recur
           (inc i)
           (loop [j 1, dp dp]
             (if (> j ly)
               dp
               (recur
                (inc j)
                (loop [k 1, dp dp]
                  (if (> k lz)
                    dp
                    (let [val (if (and (= xi (nth y (dec j)))
                                       (= xi (nth z (dec k))))
                                (inc (get-in dp [(dec i) (dec j) (dec k)]))
                                (apply max
                                       (get-in dp [(dec i) j k])
                                       (get-in dp [i (dec j) k])
                                       (get-in dp [i j (dec k)])))]
                      (recur (inc k) (assoc-in dp [i j k] val))))))))))))))