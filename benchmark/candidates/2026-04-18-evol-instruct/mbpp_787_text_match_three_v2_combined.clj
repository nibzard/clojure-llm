(defn text_match_three-or-more
  "Return true if the input string contains an 'a' followed by three or more consecutive 'b' characters anywhere in the string.

Examples:
  (text_match_three-or-more \"xxabbbzz\") => true
  (text_match_three-or-more \"abbbbb\") => true
  (text_match_three-or-more \"xxabbz\") => false
  (text_match_three-or-more nil) => false"
  [text]
  (boolean (re-find #"ab{3,}" (or text ""))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= true (text_match_three-or-more "xxabbbzz")))
  (is (= true (text_match_three-or-more "abbbbb")))
  (is (= false (text_match_three-or-more "xxabbz")))
  (is (= false (text_match_three-or-more nil))))

(run-test test-variation)
