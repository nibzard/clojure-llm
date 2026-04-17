(require '[clojure.test :refer [deftest is run-test]])

(def candidate capital_words_spaces)

(deftest test-humaneval

  (is (= (candidate "Python") "Python"))
  (is (= (candidate "PythonProgrammingExamples") "Python Programming Examples"))
  (is (= (candidate "GetReadyToBeCodingFreak") "Get Ready To Be Coding Freak"))
)

(run-test test-humaneval)