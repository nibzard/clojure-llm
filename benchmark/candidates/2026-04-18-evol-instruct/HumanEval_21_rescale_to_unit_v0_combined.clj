(defn normalize-keywords
  "Given a collection of keywords, return a vector of lower-case keyword names
  sorted in ascending order by their original keyword names.

  Preserve duplicates. Return an empty vector for nil or empty input.

  Examples:
  >>> (normalize-keywords [:Banana :apple :Apple])
  [\"apple\" \"apple\" \"banana\"]
  >>> (normalize-keywords nil)
  []"
  [ks]
  (->> ks
       (map #(-> % name clojure.string/lower-case))
       sort
       vec))

(require '[clojure.test :refer [deftest is run-test]])

(deftest test-variation
  (is (= ["apple" "apple" "banana"]
         (normalize-keywords [:Banana :apple :Apple])))
  (is (= []
         (normalize-keywords nil))))

(run-test test-variation)
