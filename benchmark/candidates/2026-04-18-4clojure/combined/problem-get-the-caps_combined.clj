(defn problem-get-the-caps [a b]
  (apply str (filter #(Character/isUpperCase ^char %) a)))

(require '[clojure.test :refer [deftest is run-tests]])

(deftest test-problem-get-the-caps
  (is (= (= (problem-get-the-caps "HeLlO, WoRlD!") "HLOWRD")))
  (is (= (empty? (problem-get-the-caps "nothing"))))
  (is (= (= (problem-get-the-caps "$#A(*&987Zf") "AZ"))))

(run-tests)
