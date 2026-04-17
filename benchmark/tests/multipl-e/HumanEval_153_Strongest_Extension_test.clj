(require '[clojure.test :refer [deftest is run-test]])

(def candidate Strongest_Extension)

(deftest test-humaneval

  (is (= (candidate "Watashi" ["tEN" "niNE" "eIGHt8OKe"]) "Watashi.eIGHt8OKe"))
  (is (= (candidate "Boku123" ["nani" "NazeDa" "YEs.WeCaNe" "32145tggg"]) "Boku123.YEs.WeCaNe"))
  (is (= (candidate "__YESIMHERE" ["t" "eMptY" "nothing" "zeR00" "NuLl__" "123NoooneB321"]) "__YESIMHERE.NuLl__"))
  (is (= (candidate "K" ["Ta" "TAR" "t234An" "cosSo"]) "K.TAR"))
  (is (= (candidate "__HAHA" ["Tab" "123" "781345" "-_-"]) "__HAHA.123"))
  (is (= (candidate "YameRore" ["HhAas" "okIWILL123" "WorkOut" "Fails" "-_-"]) "YameRore.okIWILL123"))
  (is (= (candidate "finNNalLLly" ["Die" "NowW" "Wow" "WoW"]) "finNNalLLly.WoW"))
  (is (= (candidate "_" ["Bb" "91245"]) "_.Bb"))
  (is (= (candidate "Sp" ["671235" "Bb"]) "Sp.671235"))
)

(run-test test-humaneval)