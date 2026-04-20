(defn last-upper
  "Return the last uppercase character in a string, or nil if there is none.

  Examples:
  (last-upper \"cljTHON\") ;=> \\N
  (last-upper \"abc\")     ;=> nil
  (last-upper nil)        ;=> nil"
  [s]
  (when s
    (last (filter #(Character/isUpperCase ^char %) s))))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= \N (last-upper "cljTHON")))
  (is (= nil (last-upper "abc")))
  (is (= nil (last-upper nil))))

(run-test test-variation)
