(defn lcs-of-seqs
  "Return the longest common subsequence shared by three sequences.

  The inputs may be any sequential collections (not just strings), including
  vectors and lists. Return the result as a vector.

  Examples:
  (lcs-of-seqs [1 2 3 4] [2 4 3 1] [2 3 4]) ;=> [2 3]
  (lcs-of-seqs \"abcde\" \"ace\" \"aec\")     ;=> [\\a \\e]
  (lcs-of-seqs [] [1 2] [1])                ;=> []"
  [a b c]
  (let [a (vec a)
        b (vec b)
        c (vec c)
        la (count a)
        lb (count b)
        lc (count c)
        dp (vec (for [i (range (inc la))]
                  (vec (repeat (inc lb) (vec (repeat (inc lc) []))))))]
    (loop [i 1 dp dp]
      (if (> i la)
        (get-in dp [la lb lc])
        (recur
         (inc i)
         (loop [j 1 dp dp]
           (if (> j lb)
             dp
             (recur
              (inc j)
              (loop [k 1 dp dp]
                (if (> k lc)
                  dp
                  (let [ai (nth a (dec i))
                        bj (nth b (dec j))
                        ck (nth c (dec k))]
                    (assoc-in dp [i j k]
                              (if (= ai bj ck)
                                (conj (get-in dp [(dec i) (dec j) (dec k)]) ai)
                                (let [x (get-in dp [(dec i) j k])
                                      y (get-in dp [i (dec j) k])
                                      z (get-in dp [i j (dec k)])]
                                  (apply max-key count [x y z])))))))))))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [\a \e] (lcs-of-seqs "abcde" "ace" "aec"))))

(run-test test-variation)
