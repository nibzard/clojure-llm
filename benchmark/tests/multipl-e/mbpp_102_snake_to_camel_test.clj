(require '[clojure.test :refer [deftest is run-test]])

(def candidate snake_to_camel)

(deftest test-humaneval

  (is (= (candidate "python_program") "PythonProgram"))
  (is (= (candidate "python_language") "PythonLanguage"))
  (is (= (candidate "programming_language") "ProgrammingLanguage"))
)

(run-test test-humaneval)