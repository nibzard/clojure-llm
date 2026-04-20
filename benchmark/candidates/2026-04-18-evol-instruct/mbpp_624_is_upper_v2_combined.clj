(defn is_upper
  "Return a new string with all alphabetic characters converted to uppercase.

  Examples:
  (is_upper \"clj\") => \"CLJ\"
  (is_upper nil)    => nil"
  [string]
  (when string
    (.toUpperCase string)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= "CLJ" (is_upper "clj")))
  (is (= "HELLO, WORLD!" (is_upper "Hello, World!")))
  (is (= nil (is_upper nil))))

(run-test test-variation)
