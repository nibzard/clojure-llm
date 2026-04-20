(defn even-indexed-chars
  "Return a string containing only the characters at even indexes (0, 2, 4, ...).

  Works with nil by returning an empty string.

  Examples:
  (even-indexed-chars \"abcdef\") ;=> \"ace\"
  (even-indexed-chars \"a\")      ;=> \"a\"
  (even-indexed-chars nil)       ;=> \"\""
  [s]
  (apply str (keep-indexed (fn [i ch] (when (even? i) ch)) (or s ""))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "ace" (even-indexed-chars "abcdef")))
  (is (= "a" (even-indexed-chars "a")))
  (is (= "" (even-indexed-chars nil))))

(run-test test-variation)
