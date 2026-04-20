(defn even-indexed-chars
  "Return a string containing only the characters at even indexes (0, 2, 4, ...).

  Examples:
  (even-indexed-chars \"abcdef\") ;=> \"ace\"
  (even-indexed-chars \"hello\")   ;=> \"hlo\"
  (even-indexed-chars nil)        ;=> \"\""
  [s]
  (apply str (keep-indexed (fn [i ch] (when (even? i) ch)) (or s ""))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "ace" (even-indexed-chars "abcdef")))
  (is (= "hlo" (even-indexed-chars "hello")))
  (is (= "" (even-indexed-chars nil))))

(run-test test-variation)
