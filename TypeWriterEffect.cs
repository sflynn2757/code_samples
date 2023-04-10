using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using TMPro;


public class TypeWriterEffect : MonoBehaviour
{

    [SerializeField] private float typewriterSpeed = 50f;

    public Coroutine Run(string textToType, TMP_Text textLabel) {

        return StartCoroutine(TypeText(textToType, textLabel));
    }

    private IEnumerator TypeText(string textToType, TMP_Text textLabel) {

        textLabel.text = string.Empty;

        

        float t = 0; //elapsed time
        int charIndex = 0; //character enumerator

        while (charIndex < textToType.Length) { //while we havent written the whole message

            t += Time.deltaTime * typewriterSpeed; //increments time
            charIndex = Mathf.FloorToInt(t); //makes it a whole number
            charIndex = Mathf.Clamp(charIndex, 0, textToType.Length); //maxes out var at the length of the text

            textLabel.text = textToType.Substring(0, charIndex);

            yield return null;
        }

        textLabel.text = textToType; //sanity check to ensure at the end of the animation the whole message is shown

    }

}
