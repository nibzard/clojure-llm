(defn parse_base
  "Parse a string representing a non-negative integer in the given base and return its decimal value.

  The input may contain leading zeros and mixed-case digits for bases above 10.
  If the string is nil or empty, return nil.

  Examples:
  >>> (parse_base \"22\" 3)
  8
  >>> (parse_base \"1000\" 2)
  8
  >>> (parse_base \"ff\" 16)
  255
  >>> (parse_base nil 10)
  nil"
  [s base]
  (when (and s (not (empty? s)))
    (Long/parseLong s base)))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= 8 (parse_base "22" 3)))
  (is (= 8 (parse_base "1000" 2)))
  (is (= 255 (parse_base "ff" 16)))
  (is (= nil (parse_base nil 10))))

(run-test test-variation)
