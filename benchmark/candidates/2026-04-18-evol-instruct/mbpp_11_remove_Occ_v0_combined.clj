(defn strip-first-last
  "Return a vector with the first and last occurrence of `x` removed from `xs`.

  Works on any sequential collection. If `x` appears once, remove that one item.
  If `x` does not appear, return the collection as a vector unchanged.

  Examples:
  (strip-first-last [1 2 3 2 4] 2) => [1 3 2 4]
  (strip-first-last [\"a\" \"b\" \"a\" \"c\"] \"a\") => [\"b\" \"a\" \"c\"]"
  [xs x]
  (let [v (vec xs)
        first-idx (.indexOf v x)
        last-idx (.lastIndexOf v x)]
    (if (neg? first-idx)
      v
      (vec (concat (subvec v 0 first-idx)
                   (subvec v (inc first-idx) last-idx)
                   (subvec v (inc last-idx)))))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= [1 3 2 4] (strip-first-last [1 2 3 2 4] 2)))
  (is (= ["b" "a" "c"] (strip-first-last ["a" "b" "a" "c"] "a"))))

(run-test test-variation)
