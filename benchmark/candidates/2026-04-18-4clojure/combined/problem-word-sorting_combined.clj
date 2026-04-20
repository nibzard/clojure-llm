(defn problem-word-sorting
  [a b c d e]
  (->> (or a "")
       (re-seq #"[A-Za-z]+")
       (sort-by clojure.string/lower-case)
       vec))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-word-sorting
  (is (= (= (problem-word-sorting  "Have a nice day.")
   ["a" "day" "Have" "nice"])))
  (is (= (= (problem-word-sorting  "Clojure is a fun language!")
   ["a" "Clojure" "fun" "is" "language"])))
  (is (= (= (problem-word-sorting  "Fools fall for foolish follies.")
   ["fall" "follies" "foolish" "Fools" "for"]))))

(run-tests)
