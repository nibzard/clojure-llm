(defn count-distinct-subsequences
  "Write a Clojure function to count the number of distinct non-empty subsequences of a string.

  The input may be a string or nil. Return 0 for nil or the empty string.

  Examples:
  (count-distinct-subsequences \"abc\") => 7
  ;; \"a\" \"b\" \"c\" \"ab\" \"ac\" \"bc\" \"abc\"

  (count-distinct-subsequences \"aaa\") => 3
  ;; \"a\" \"aa\" \"aaa\"

  (count-distinct-subsequences nil) => 0"
  [s]
  (if (seq s)
    (let [chars (vec s)
          n (count chars)
          dp (transient [1])]
      (loop [i 0
             dp dp
             last-seen {}]
        (if (= i n)
          (dec (persistent! dp) 0)
          (let [c (chars i)
                total (reduce + (persistent! dp))
                new-total (if-let [j (last-seen c)]
                            (- (* 2 total) (nth (persistent! dp) j))
                            (* 2 total))
                dp' (conj! dp new-total)]
            (recur (inc i) dp' (assoc last-seen c (count (persistent! dp))))))))
    0))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 7 (count-distinct-subsequences "abc")))
  (is (= 3 (count-distinct-subsequences "aaa")))
  (is (= 0 (count-distinct-subsequences nil))))

(run-test test-variation)
