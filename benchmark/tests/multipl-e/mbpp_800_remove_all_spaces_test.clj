(require '[clojure.test :refer [deftest is run-test]])

(def candidate remove_all_spaces)

(deftest test-humaneval

  (is (= (candidate "python  program") "pythonprogram"))
  (is (= (candidate "python   programming    language") "pythonprogramminglanguage"))
  (is (= (candidate "python                     program") "pythonprogram"))
  (is (= (candidate "   python                     program") "pythonprogram"))
)

(run-test test-humaneval)