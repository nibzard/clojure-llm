(defn contains-subsequence?
  "Return true if `needle` appears as a contiguous subsequence of `haystack`.

  Works on any sequential collection, including vectors and lazy sequences.

  Examples:
  (contains-subsequence? [1 2 3 4] [2 3]) ;=> true
  (contains-subsequence? [1 2 3 4] [3 2]) ;=> false
  (contains-subsequence? (range 10) [4 5 6]) ;=> true
  (contains-subsequence? [1 2 3] []) ;=> true"
  [haystack needle]
  (let [h (seq haystack)
        n (seq needle)]
    (cond
      (nil? n) true
      (nil? h) false
      :else
      (let [hn (count needle)]
        (boolean
          (some true?
                (map #(= needle (take hn %))
                     (partition hn 1 haystack))))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (contains-subsequence? [1 2 3 4] [2 3]))))

(run-test test-variation)
