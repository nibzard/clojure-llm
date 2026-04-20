(defn common-subsequence
  "Return the longest common subsequence of three sequences.

  The inputs may be strings or vectors. Return the result as a vector of
  elements from the sequences. If any input is nil, treat it as an empty sequence.

  Examples:
  (common-subsequence \"abcdef\" \"acbcf\" \"abcf\") ;=> [\\a \\b \\c \\f]
  (common-subsequence [1 2 3 4] [2 4 3 1] [5 2 3 4]) ;=> [2 3]"
  [a b c]
  (let [a (vec (or a []))
        b (vec (or b []))
        c (vec (or c []))
        m (count a)
        n (count b)
        p (count c)
        dp (vec (for [i (range (inc m))]
                  (vec (for [j (range (inc n))]
                         (vec (repeat (inc p) []))))))]
    (loop [i 1 dp dp]
      (if (> i m)
        (get-in dp [m n p])
        (let [dp (loop [j 1 dp dp]
                   (if (> j n)
                     dp
                     (let [dp (loop [k 1 dp dp]
                                (if (> k p)
                                  dp
                                  (assoc-in dp [i j k]
                                            (if (= (nth a (dec i))
                                                   (nth b (dec j))
                                                   (nth c (dec k)))
                                              (conj (get-in dp [(dec i) (dec j) (dec k)])
                                                    (nth a (dec i)))
                                              (let [x (get-in dp [(dec i) j k])
                                                    y (get-in dp [i (dec j) k])
                                                    z (get-in dp [i j (dec k)])]
                                                (apply max-key count [x y z])))))
                                (recur (inc k) dp)))]
                       (recur (inc j) dp)))]
          (recur (inc i) dp))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [\a \b \c \f]
         (common-subsequence "abcdef" "acbcf" "abcf")))
  (is (= [2 3]
         (common-subsequence [1 2 3 4] [2 4 3 1] [5 2 3 4])))
  (is (= []
         (common-subsequence nil "abc" "abc"))))

(run-test test-variation)
