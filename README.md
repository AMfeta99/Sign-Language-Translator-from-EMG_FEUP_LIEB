# Sign-Language-Translator-from-EMG_FEUP_LIEB

This repository is dedicated to the LIED 2019/2020 project. The objective of the project was to come up with an idea to solve a biomedical problem or develop a biomedical tool/device/app using all the software/hardware necessary for development from scratch of a complete/functional prototype.

## Goal
We aimed to development of an app that corresponds to a prototype of a sign language translator and the traditional game Rock Paper Scissors (using EMG signals).

## Approach
The electromyography (EMG) signal has been widely used both in the medical field for diagnosis and for identifying movements with a view, for example, to controlling devices such as prostheses. To identify the 3 gestures in question, 3 surface electrodes were placed on the forearm and developed an analog circuit that allows its acquisition and processing in order to obtain the EMG signal
integrated (IEMG). 

This is a key element in determining the gesture since each muscle is associated with a threshold value that determines whether the captured signal corresponds to the muscle in the active or inactive state, which combining the two channels allows you to determine the position of the hand. The processed analog signal is converted to digital from the Arduino and information about the activation state is sent to Java, where the gesture identification algorithm and the translator/game are implemented. 

## Scripts/files:
- Analog Circuit in Multisim (design and simulation of  analogic circuit that allows its EMG acquisition and processing )
- Pedra_Papel_Tesoura (Java project that contains a set of scripts/files of the app)
- LIEB_report (Final reports describing, literature, methods/development including EMG acquisition, processing , convertion to digital (Arduino), app design/operation , results)
- PP_LIEB (slides used to support the oral/final presentation)

## Results:
The results demonstrated the possibility of using this signal to identify gestures of a in a simple and quick way, whether for application as a translator or for implementing the game in question.
